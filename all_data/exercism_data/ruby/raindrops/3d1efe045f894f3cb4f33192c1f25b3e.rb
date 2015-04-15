class Raindrops
  TRANSLATIONS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(num)
    result = ""
    TRANSLATIONS.each do |factor, drop|
      result << drop if (num % factor).zero?
    end
    result << num.to_s if result.empty?
    result
  end
end
