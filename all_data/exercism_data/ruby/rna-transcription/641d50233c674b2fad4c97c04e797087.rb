class Complement
  def self.of_dna(letters)
    letters.split('').map(&Dna.method(:new)).map(&:complement).join()
  end

  def self.of_rna(letters)
    letters.split('').map(&Rna.method(:new)).map(&:complement).join()
  end

  class Dna
    def initialize(letter)
      @letter = letter
    end

    def complement
      case @letter
      when "G"
        "C"
      when "C"
        "G"
      when "T"
        "A"
      when "A"
        "U"
      end
    end
  end

  class Rna
    def initialize(letter)
      @letter = letter
    end

    def complement
      case @letter
      when "C"
        "G"
      when "G"
        "C"
      when "A"
        "T"
      when "U"
        "A"
      end
    end
  end
end
