class ETL
  def self.transform(old_hash)
    old_hash.each_pair.with_object({}) do |(score, letters), result|
      letters.each { |l| result[l.downcase] = score }
    end
  end
end
