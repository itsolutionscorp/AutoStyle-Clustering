class Complement
  def self.of_dna(strand)
    strand_type = "dna"
    letters = strand.chars
    results = letters.map do |letter|
      match_letter(strand_type, letter)
    end
    results.join
  end

  def self.of_rna(strand)
    strand_type = "rna"
    letters = strand.chars
    results = letters.map do |letter|
      match_letter(strand_type, letter)
    end
    results.join
  end

  def self.match_letter(strand_type, letter)
    if strand_type == "dna"
      Match.new(letter).match
    else
      MatchRNA.new(letter).match
    end
  end
end

class Match
  attr_reader :letter

  def initialize(letter)
    @letter = letter
  end

  def match
    Kernel.const_get("Letter#{letter.upcase}").new.result
  end
end

class MatchRNA < Match
  def match
    if letter == 'A'
      RNALetterA.new.result
    else
      Kernel.const_get("Letter#{letter.upcase}").new.result
    end
  end
end

class LetterC
  def result
    'G'
  end
end

class LetterG
  def result
    'C'
  end
end

class LetterT
  def result
    'A'
  end
end

class LetterU
  def result
    'A'
  end
end

class LetterA
  def result
    'U'
  end
end

class RNALetterA
  def result
    'T'
  end
end
