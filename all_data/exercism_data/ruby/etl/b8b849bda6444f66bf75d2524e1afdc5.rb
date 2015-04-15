class ETL
  def self.transform(legacy)
    shiny={}
    legacy.each do |s,v|
      v.each { |l| shiny[l.downcase()]=s }
    end
    shiny
  end
end
