class Bob
  def hey(message)
    appropriate_response_for(message).response
  end

  def appropriate_response_for(message)
    possible_responses.find { |response| response.match?(message) }
  end

  def possible_responses
    @responses ||= [

      response(when_the_message_matches: -> msg { msg == '' },
               i_would_say: 'Fine. Be that way.',
               because: "Unbearable Silence Breeds Resentment"),

      response(when_the_message_matches: -> msg { msg == msg.upcase },
               i_would_say: 'Woah, chill out!',
               because: "Yelling Only Pushes Me Away"),

      response(when_the_message_matches: -> msg { msg.end_with?("?") },
               i_would_say: 'Sure.',
               because: "I Give Apathetic Answers to Stupid Questions"),

      response(when_the_message_matches: -> msg { true },
               i_would_say: 'Whatever.',
               because: "I Heard You But I Did Not Listen")

    ]
  end

  def response(params)
    CallAndResponse.new params[:because],
      params[:when_the_message_matches],
      params[:i_would_say]
  end

  class CallAndResponse
    attr_reader :name, :match, :response

    def initialize(name, match, response)
      @name = name
      @match = match
      @response = response
    end

    def match?(message)
      match.call(message)
    end
  end
end
