class Message
  include StringValidations
  attr_reader :message

  def initialize(message)
    super()
    @message = message
  end

  def validate_to_respond
    return 'Fine. Be that way!' if @message.nil? or @message==''
    return 'Whatever.' if contains_hyphens_and_period_at_end?(@message) or (contains_exclamation_mark?(@message) and !contains_uppercase_words?(@message))
    return 'Woah, chill out!' if contains_uppercase_words?(@message) and !(contains_ok?(@message) and contains_question_mark?(@message))
    return 'Sure.' if contains_question_mark?(@message)
  end

end
