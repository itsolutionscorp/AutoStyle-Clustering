class ETL

  def ETL.transform(old)
    new_point_map = old.inject({}) do |h, (k, v)| 
      v.each do |letter| 
        h[letter.downcase] = k 
      end 
      h
    end
  end

end
