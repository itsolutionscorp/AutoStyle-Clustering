class ETL
  def self.transform(data)
    new_pairs = data.each_with_object([]) do |(score, chars), pairs|
      chars.map{|char| pairs << [char.downcase, score]}
    end
    Hash[new_pairs.flatten(1)]
  end
end
