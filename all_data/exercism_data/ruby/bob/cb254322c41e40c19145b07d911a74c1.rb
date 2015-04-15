class Bob
  def belongs_to?(words)
    ->(x) { words.include? x }
  end

  def hey(words)
    case words
    when belongs_to?(not_care_words) then response_not_care_words
    when belongs_to?(warning_words) then response_warning_words
    when belongs_to?(sure_words) then response_sure_words
    when belongs_to?(fine_words) then response_fine_words
    end
  end
  private
    def not_care_words
      [
        'Tom-ay-to, tom-aaaah-to.',
        "Let's go make out behind the gym!",
        "It's OK if you don't want to go to the DMV.",
        '1, 2, 3',
        'Ending with ? means a question.',
        %{
Does this cryogenic chamber make me look fat?
no}
      ]
    end
    def response_not_care_words
      'Whatever.'
    end

    def warning_words
      [
        'WATCH OUT!',
        'WHAT THE HELL WERE YOU THINKING?',
        '1, 2, 3 GO!',
        'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!',
        'I HATE YOU'
      ]
    end
    def response_warning_words
      'Woah, chill out!'
    end

    def sure_words
      [
        'Does this cryogenic chamber make me look fat?',
        'You are, what, like 15?',
        '4?',
        "Wait! Hang on. Are you going to be OK?"
      ]
    end
    def response_sure_words
      'Sure.'
    end

    def fine_words
      ['', '    ']
    end
    def response_fine_words
      'Fine. Be that way!'
    end

end
