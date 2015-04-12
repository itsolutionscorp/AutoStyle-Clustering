class Hamming
  def compute(first, second)
    score = [0]
    (0..first.length-1).each do |c|
      first[c] == second[c] ? 0 : score << 1
    end
    score.inject {|e,b| e + b }
  end
end
