class Bob

  def hey msg
    @msg = un_numerator(msg)
    respond
  end

  def respond
    response_to = {
      "general"  => "Whatever.",
      "yelling"  => "Woah, chill out!",
      "question" => "Sure.",
      "quiet"    => "Fine. Be that way!"
    }

    if person_is_asking_a_question?
      response_to["question"]
    elsif person_is_yelling?
      response_to["yelling"]
    elsif person_is_quiet?
      response_to["quiet"]
    else
      response_to["general"]
    end
  end

  def person_is_yelling?
    ((last_letter == "!" || last_letter == "?") && all_caps) || all_caps
  end

  def person_is_asking_a_question?
    last_letter == "?" unless all_caps
  end

  def person_is_quiet?
    @msg.gsub(/\s/, '').empty?
  end

  private

    def last_letter
      @msg.slice(-1)
    end

    def all_caps
      @msg == @msg.upcase && contains_letters
    end

    def un_numerator msg
      msg.gsub /[0-9]/, ''
    end

    def contains_letters
      @msg.match /[A-Za-z]/
    end
end
