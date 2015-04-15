class ETL
  def ETL.transform (a)
    b = {}
    a.each{|k, v|
      v.each{|w|
        b[w.downcase] = k
      }
    }
    b
  end
end
