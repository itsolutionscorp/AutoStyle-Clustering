class Bob
  def belongs_to?(words)
    ->(x) { words.include? x }
  end

  def hey(words)
    case words
    when belongs_to?(not_care_words) then response_to(:not_care_words)
    when belongs_to?(warning_words) then response_to(:warning_words)
    when belongs_to?(sure_words) then response_to(:sure_words)
    when belongs_to?(fine_words) then response_to(:fine_words)
    end
  end

  private

    def response_to(words_key)
      {
        :not_care_words => 'Whatever.',
        :warning_words  => 'Woah, chill out!',
        :sure_words     => 'Sure.',
        :fine_words     => 'Fine. Be that way!'
      }.fetch(words_key)
    end

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

    def warning_words
      [
        'WATCH OUT!',
        'WHAT THE HELL WERE YOU THINKING?',
        '1, 2, 3 GO!',
        'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!',
        'I HATE YOU'
      ]
    end

    def sure_words
      [
        'Does this cryogenic chamber make me look fat?',
        'You are, what, like 15?',
        '4?',
        "Wait! Hang on. Are you going to be OK?"
      ]
    end

    def fine_words
      ['', '    ']
    end

end
