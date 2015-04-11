class ETL
  def self.transform(old)
    new = {}
    for i in 0...11
      if old.has_key?(i)
        corresp =  old[i]
        corresp.each {|x| new.store(x.downcase, i)}
      end
    end
    return new
  end
end
