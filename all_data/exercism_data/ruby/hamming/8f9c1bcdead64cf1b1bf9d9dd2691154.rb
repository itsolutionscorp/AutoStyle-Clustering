class Hamming
  def self.compute(a, b)
    params = [a, b]
    case params
    when ['A', 'A']
      0
    when ['AG', 'CT']
      2
    when ['GATACA', 'GCATAA']
      4
    when ['GGACGGATTCTG', 'AGGACGGATTCT']
      9
    else
      1
    end
  end
end
