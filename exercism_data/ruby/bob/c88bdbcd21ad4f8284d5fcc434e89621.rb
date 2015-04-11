class Bob
  def hey(phrase)
    message = Message.new(phrase)

    send("#{message.response_type}_response".to_sym)
  end

  private

  def lazy_reponse
  end

  def forceful_response


    # return "Fine. Be that way!" if phrase.strip.empty?

    # is_question = (phrase.chars[-1] == "?")
    # is_forceful = !(phrase.match(/[a-z]+/))

    # if is_forceful
    #   "Woah, chill out!"
    # elsif is_question
    #   "Sure."
    # else
    #   "Whatever."
    # end
  end
end

class Message

end
