class ETL
  def ETL.transform(old)  
    old.collect { |score,ary| ary.collect { |letter| { letter.downcase => score } } }.flatten.inject(:merge)
  end
end
