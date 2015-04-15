module ETL

  def self.transform old
    old.each_with_object({}) do |(score, letters), memo|
      letters.each { |l| memo[l.downcase] = score }
    end
  end
end
