class ETL
  
  def self.transform( letters_per_score_db )
    letters_per_score_db.each_with_object ({}) do |(score,letters), score_per_letter| 
       letters.each do |a_letter|
        score_per_letter[a_letter.downcase] = score
      end  
    end
  end  
  
end    
