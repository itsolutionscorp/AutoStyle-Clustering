class ETL
  def self.transform(old)
    old.each_with_object({}) do |(score, letters), new_map|
      letters.each do|letter|
        new_map[letter.downcase] = score
      end
    end
  end
end
