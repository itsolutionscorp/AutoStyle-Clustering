class ETL

  def self.transform(old_system)
    new_system = {}
    old_system.each do |point, letter|
      letter.each do |l|
        new_system[l.downcase] = point
      end
    end
    new_system
  end

end
