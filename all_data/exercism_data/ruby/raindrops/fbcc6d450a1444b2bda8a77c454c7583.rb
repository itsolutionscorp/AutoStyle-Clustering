class Integer
  def factor?(n)
    self % n == 0
  end
end

module Raindrops
  module_function

  TRANSLATIONS = { 'Pling' => 3, 'Plang' => 5, 'Plong' => 7 }

  def sing(n)
    TRANSLATIONS.keys.select do |word|
      n.factor?(TRANSLATIONS[word])
    end
  end

  def convert(n)
    return n.to_s unless knows_song?(n)
    sing(n).join
  end

  def knows_song?(n)
    TRANSLATIONS.values.any? { |raindrop| n.factor?(raindrop) }
  end

end
