# encoding: UTF-8
# Clase ETL
class ETL
  def self.transform(oldhash)
    e = {}
    oldhash.each do |k , v|
      v.each do |l|
        e[l.downcase] = k
      end
    end
    e
  end
end
