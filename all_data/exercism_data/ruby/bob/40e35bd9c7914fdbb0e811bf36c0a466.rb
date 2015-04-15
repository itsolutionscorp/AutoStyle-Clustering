class Bob

  ANSWERS = {
    yelling: "Woah, chill out!",
    silence: "Fine. Be that way!",
    question: "Sure.",
    anything_else: "Whatever."
  }

  def hey(msg)
    @msg = msg

    if is_silence?
      answer_for_silence
    elsif is_yelling?
     answer_for_yelling
    elsif is_asking?
      answer_for_question
    else
      answer_for_anything_else
    end
  end

  private

  def is_silence?
    @msg.gsub(/\s/, "").empty?
  end

  def is_yelling?
    @msg.upcase == @msg
  end

  def is_asking?
    @msg.match(/.*\?\z/)
  end

  ANSWERS.each do |input_type, answer|
    define_method "answer_for_#{input_type}" do
      answer
    end
  end

end
