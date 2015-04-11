class Bob
  def hey(words)
    case words
    when is?(:empty) then responds_to(:empty)
    when is?(:shout) then responds_to(:shout)
    when is?(:ask)   then responds_to(:ask)
    else responds_to(:whatever)
    end
  end

  private

    def responds_to(words_key)
      {
        :whatever => 'Whatever.',
        :shout    => 'Woah, chill out!',
        :ask      => 'Sure.',
        :empty    => 'Fine. Be that way!'
      }.fetch(words_key)
    end

    def is?(rule)
      ->(w) { send(rule.to_s + "?", w) }
    end

    def ask?(words)
      words.end_with?('?')
    end

    def empty?(words)
      words.strip.empty?
    end

    def shout?(words)
      words =~ /[A-Z]/ && words.upcase == words
    end

end
