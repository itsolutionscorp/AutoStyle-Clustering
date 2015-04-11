module ETL
  def self.transform(old)
    old.each_with_object({}) do |(score, letters), h|
      letters.each { |l| h[l.downcase] = score }
    end
  end
end
