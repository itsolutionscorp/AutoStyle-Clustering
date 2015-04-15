class Fixnum
  def to_roman
    thousands + hundreds + tens + ones
  end

  private
    def thousands
      "M" * (self / 1000)
    end

    def hundreds
      roman_guidelines("C", "D", "M")[(self % 1000) / 100]
    end

    def tens
      roman_guidelines("X", "L", "C")[(self % 100) / 10]
    end

    def ones
      roman_guidelines("I", "V", "X")[self % 10]
    end

    def roman_guidelines(one, five, ten)
      [
        "",
        "#{one}",
        "#{one}#{one}",
        "#{one}#{one}#{one}",
        "#{one}#{five}",
        "#{five}",
        "#{five}#{one}",
        "#{five}#{one}#{one}",
        "#{five}#{one}#{one}#{one}",
        "#{one}#{ten}"
      ]
    end
end
