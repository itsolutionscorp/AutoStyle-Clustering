class Phrase < String
  def word_count
    downcase.scan(/\w+/).each_with_object(Hash.new(0)) { |e, a| a[e] += 1 }
  end
end
