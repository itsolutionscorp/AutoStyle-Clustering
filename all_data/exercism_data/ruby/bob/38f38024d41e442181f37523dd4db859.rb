# monkey patching String instance
class Bob
  def hey(phrase)

    class << phrase
      def question?
        end_with?('?')
      end

      def calm?
        gsub(/[\W\d]/,'').chars.any? { |l| /[a-z]/.match(l)  }
      end

      def angry?
        !calm?
      end

      def has_only_numbers?
        gsub(/[\W]/,'').chars.all? { |n| n =~ /[0-9]/  }
      end
    end

    phrase.strip!
    return 'Sure.' if phrase.question? && (phrase.calm? || phrase.has_only_numbers?)
    return 'Fine. Be that way!' if phrase.empty?
    return 'Whoa, chill out!' if phrase.angry? && !phrase.has_only_numbers?
    'Whatever.'
  end

end
