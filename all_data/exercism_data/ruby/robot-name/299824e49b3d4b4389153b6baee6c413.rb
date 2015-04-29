class Robot
  def name
    unless @name
      @name = "#{ALLTWOLETTERCOMBOS.next}#{NUMBERS.next}"
    end
    @name
  end

  def random(array)
    array[rand(array.length-1)]
  end

  def reset
    @name = nil
  end


  class MixedCaseTwoLetterGenerator
    def self.lowers_to_uppers
      self.new(("a".."z"), ("A".."Z"))
    end

    def self.uppers_to_lowers
      self.new(("A".."Z"), ("a".."z"))
    end

    def initialize(first_chars, last_chars)
      @first_chars = first_chars.each
      @last_chars = last_chars.cycle
      @first_character_of_last_chars = @last_chars.peek
    end

    def next
      if @last_chars.peek == @first_character_of_last_chars
        @firstchar = @first_chars.next
      end
      "#{@firstchar}#{@last_chars.next}"
    end
  end

  class TwoLetterNameGenerator
    LOWERS = ("aa".."zz").each
    UPPERS = ("AA".."ZZ").each
    LOWERUPPERS = MixedCaseTwoLetterGenerator.lowers_to_uppers
    UPPERLOWERS = MixedCaseTwoLetterGenerator.uppers_to_lowers

    def LOWERS.next_enumerator
      UPPERS
    end

    def UPPERS.next_enumerator
      LOWERUPPERS
    end

    def LOWERUPPERS.next_enumerator
      UPPERLOWERS
    end

    def UPPERLOWERS.next_enumerator
      raise "all codes exhausted"
    end

    def next
      @current_enumerator ||= LOWERS
      begin
        @current_enumerator.next
      rescue StopIteration
        @current_enumerator = @current_enumerator.next_enumerator
        self.next
      end
    end
  end


  NUMBERS = ("000".."999").cycle
  ALLTWOLETTERCOMBOS = TwoLetterNameGenerator.new
end
