class Hamming
  def self.compute(strand_a, strand_b)
    @distance = 0

    strand_a,strand_b = truncate_strings_to_identical_size(strand_a, strand_b)

    if strand_a == strand_b
      return @distance
    else
      strand_a.chars.each_with_index do |character, index|
        if character.eql? strand_b[index]
          next
        else
          @distance += 1
        end
      end
    end
    @distance
  end

  def self.truncate_strings_to_identical_size(string_a, string_b)
    comparision = string_a <=> string_b
    if comparision.eql? 1
      string_a = string_a[0..(string_b.size - 1)]
    elsif comparision.eql? -1
      string_b = string_b[0..(string_a.size - 1)]
    end

    return string_a, string_b
  end
end
