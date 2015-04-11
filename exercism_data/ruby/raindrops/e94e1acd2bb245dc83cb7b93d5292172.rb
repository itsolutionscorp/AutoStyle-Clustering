class Integer
  def factor?(n)
    self % n == 0
  end
end

module Raindrops
  module_function

  TRANSALATIONS = { 'Pling' => 3, 'Plang' => 5, 'Plong' => 7 }
  def translate(n)
    TRANSALATIONS.each_with_object '' do |(word, factor), output|
      output << word if n.factor? factor
    end
  end

  def convert(n)
    translation = translate n
    if translation == ''
      n.to_s # no word for it in raindrop-speak
    else
      translation
    end
  end
end
