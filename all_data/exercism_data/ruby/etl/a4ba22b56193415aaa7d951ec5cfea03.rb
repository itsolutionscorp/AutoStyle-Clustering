class ETL
  def self.transform(old)
    old.each_with_object({}) do |(score,letters),newscore|	
      letters.each {|letter| newscore[letter.downcase]=score}     
    end
  end
  
end
