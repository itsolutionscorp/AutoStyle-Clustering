class Raindrops
  TRANSLATIONS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(num)
    result = ""
    TRANSLATIONS.each do |k,v|
      result << v if factor?(num, k)
    end
    result << num.to_s if result == ""
    result
  end

  def self.factor?(num, factor)
    (num / factor).to_f == (num.to_f / factor)
  end
end
