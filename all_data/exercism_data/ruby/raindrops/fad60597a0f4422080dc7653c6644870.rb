class Raindrops
  def convert(number)
    result = ""
    [['Pling', 3], ['Plang', 5], ['Plong', 7]].each do |(word, factor)|
      result += word if number % factor == 0
    end
    result = number.to_s if result == ""
    result
  end
end
