class ETL
  def self.transform(data)
    new_pairs = data.reduce([]) do |pairs, (score, chars)|
      pairs << chars.map{|char| [char.downcase, score]}
    end
    Hash[new_pairs.flatten(1)]
  end
end
