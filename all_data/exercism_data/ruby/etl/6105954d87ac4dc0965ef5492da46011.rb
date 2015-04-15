class ETL

  def self.transform(old)
    newScore = {}
    old.each { |score, letters|
      letters.each { |l|
        newScore[l.downcase] = score
      }
    }
    newScore
  end
end
