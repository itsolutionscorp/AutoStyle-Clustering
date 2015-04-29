class Hamming

  def self.compute(s, t)
    new.find_differences(s, t).size
  end

  def find_differences(s, t)
    pairs(normalize_and_split(s), normalize_and_split(t)).each_with_object([]) do |(chr1, chr2), a|
      a << [chr1, chr2] unless chr1 == chr2
    end
  end

  private

  def normalize_and_split(string)
    string.upcase.split(//)
  end

  def pairs(arr1, arr2)
    arr1.zip(arr2).select { |(c1, c2)| c1 && c2 }
  end

end
