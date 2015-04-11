class ETL

  def self.transform(old)
    old.each_with_object({}) do |(score, words), h|
      words.each { |w| h[w.downcase] = score }
    end
  end



end
