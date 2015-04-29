class Bob
  class Input
    attr_reader :input

    def initialize(input)
      @input = input
    end

    def question?
      (!all_uppercase? || all_numbers?) && ends_with?("?")
    end

    def yelling?
      !empty? && all_uppercase? && !all_numbers?
    end

    def empty?
      input.gsub(/\s/, '').empty?
    end

    private

    def all_numbers?
      input[0..-2].gsub(/\W/, '').chars.all? { |c|
        c.match(/[\d, ]/)
      }
    end

    def all_uppercase?
      input.chars[0..-2].all? { |c| c.upcase == c }
    end

    def ends_with?(last_char)
      input.end_with?(last_char)
    end

    def missing_punctuation?
      ! %w{, . ? !}.any? { |c|
        ends_with?(c)
      }
    end
  end

  def hey(input_string)
    input = Input.new(input_string)
    respond_to(input)
  end

  def respond_to(input)
    if input.question?
      "Sure."
    elsif input.yelling?
      "Woah, chill out!"
    elsif input.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
