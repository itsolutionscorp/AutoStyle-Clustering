class Hamming
  attr_reader :string1, :string2

  def initialize(string1, string2)
    @string1 = string1
    @string2 = string2
  end

  def self.compute(string1, string2)
    new(string1, string2).count_changes
  end

  def count_changes
    changes.count(:change)
  end

private

  def changes
    (0..range).map do |char| 
      :change unless same?(char)
    end
  end

  def same?(char)
    string1[char] == string2[char] 
  end

  def range
    string1.length
  end
end
