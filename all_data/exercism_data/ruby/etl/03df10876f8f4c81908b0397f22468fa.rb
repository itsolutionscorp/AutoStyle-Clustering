class ETL
  def self.transform old
    old.map do |k, v|
      v.map do |x|
        { x.downcase => k }
      end
    end.reduce(&:concat).reduce(&:merge)
  end
end
