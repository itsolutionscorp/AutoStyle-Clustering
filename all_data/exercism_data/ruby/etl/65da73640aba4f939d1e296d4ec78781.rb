class ETL
  
  
  def self.transform( letters_per_score_db )
    letters_per_score_db.each_with_object ({}) do |letters_per_score, score_per_letter| 
      score = letters_per_score[0]
      letters = letters_per_score[1]
       letters.each do |a_letter|
        score_per_letter[a_letter.downcase] = score
      end  
    end
  end  
  
end    
