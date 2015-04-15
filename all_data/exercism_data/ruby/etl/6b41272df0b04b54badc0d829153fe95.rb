module ETL
  def self.transform(old)
    Hash[old.map { |p, ls| ls.map { |l| [l.downcase, p] } }.flatten(1)]
  end
end
