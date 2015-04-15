# Calculate Hamming difference between 2 DNA strings
class Hamming
  def self.sort(string_1, string_2)
    if string_1.length <= string_2.length
      { shortest: string_1.chars, longest: string_2.chars, distance: 0 }
    elsif string_1.length > string_2.length
      { shortest: string_2.chars, longest: string_1.chars, distance: 0 }
    end
  end

  def self.compute(string_1, string_2)
    hash = sort(string_1, string_2)

    hash[:shortest].each do
      hash[:distance] += 1 unless hash[:shortest].next.eql? hash[:longest].next
    end
    hash[:distance]
  end
end
