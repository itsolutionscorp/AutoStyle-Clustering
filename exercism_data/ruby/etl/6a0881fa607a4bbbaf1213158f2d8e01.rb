class ETL
  def self.transform(h)
    Hash[h.map { |x| x[1].map { |y| [y.downcase, x[0]]  } }.flatten(1)]
  end
end
