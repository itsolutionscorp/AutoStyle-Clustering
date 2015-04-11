# etl.rb
class ETL
  def self.transform(crufty)
    crufty.each_with_object({}) do | (points, letters), shiny |
      letters.each do |letter|
        shiny[letter.downcase] = points
      end
    end
  end

  # Alternative, but not as clean:
  # def self.transform(crufty)
  #   crufty.flat_map do |points, letters|
  #     letters.map(&:downcase).zip([points] * letters.size)
  #   end.to_h
  # end
end
