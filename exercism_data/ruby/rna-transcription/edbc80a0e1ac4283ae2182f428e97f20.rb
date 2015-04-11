class DNA
  attr_reader :snippet
  def initialize(snippet)
    @snippet = snippet
  end

  def to_rna
    string = ''
    snippet.split(//).each do |char|
      case char
      when 'C'
        string += 'G'
      when 'G'
        string += 'C'
      when 'T'
        string += 'A'
      when 'A'
        string += 'U'
      end
    end
    string
  end
end

class RNA
  attr_reader :snippet
  def initialize(snippet)
    @snippet = snippet
  end

  def to_dna
    string = ''
    snippet.split(//).each do |char|
      case char
      when 'G'
        string += 'C'
      when 'C'
        string += 'G'
      when 'A'
        string += 'T'
      when 'U'
        string += 'A'
      end
    end
    string
  end
end

class Complement

  def self.of_dna(snippet)
    DNA.new(snippet).to_rna
  end

  def self.of_rna(snippet)
    RNA.new(snippet).to_dna
  end
end
