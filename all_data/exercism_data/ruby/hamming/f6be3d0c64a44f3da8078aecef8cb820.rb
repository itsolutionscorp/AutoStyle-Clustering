class Hamming
  def sort(string_1, string_2)
    if string_1.length <= string_2.length
      {
          :shortest => string_1.chars,
          :longest => string_2.chars,
          :distance => 0
      }
    elsif string_1.length > string_2.length
      {
          :shortest => string_2.chars,
          :longest => string_1.chars,
          :distance => 0
      }
    end
  end

  def self.compute(string_1, string_2)

    hash = sort(string_1, string_2)

    hash[:shortest].each do
      if hash[:shortest].next != hash[:longest].next
        hash[:distance] += 1
      end
    end
    hash[:distance]
  end
end
