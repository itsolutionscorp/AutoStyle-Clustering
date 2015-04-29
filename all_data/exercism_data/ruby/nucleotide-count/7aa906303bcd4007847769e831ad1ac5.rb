class Nucleotide
  def self.from_dna(string)
    string.split('').map do |letter|
      raise ArgumentError unless letter =~ /[GATC]/
    end
    string
  end

  def count(base)
    count = 0
    self.each do |letter|
      count += 1 if letter == base
    end
    count
  end
end

class String
  def histogram
    key = {
      'A' => 0,
      'T' => 0,
      'C' => 0,
      'G' => 0
    }
    self.split('').each do |char|
      key.map do |base, value|
        if char == base
          key[base] += 1
        end
      end
    end
    key
  end
end
