class ETL
  def self.transform(old_data)
    new_data = Hash.new { |hash, key| hash[key] = [] }

    old_data.each_with_object(new_data) do |(score, words), data|
      words.each { |word| data[word.downcase] = score }
    end
  end
end
