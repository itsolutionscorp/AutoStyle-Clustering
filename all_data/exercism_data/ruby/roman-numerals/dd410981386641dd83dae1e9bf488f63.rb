class ::Integer
  def to_roman
    param_hash = { num:self, string: "", patterns:{ 1=>"I", 5=>"V",
10=>"X", 50=>"L", 100=>"C", 500=>"D", 1000=>"M" }, seq:[1000, 500, 100, 50,
10, 5, 1] }

    is_rank_jump = lambda { |string, patterns, seq, index|
    string.chars.pop == patterns[seq[index - 1]] }

    substitute = lambda { |num, seq, patterns, index, string|
     if (((num / seq[index]) > 3) && (seq[index] != 1000))
       then
         if (is_rank_jump.call(string, patterns, seq, index))
           then
           string.chop << patterns[seq[index]] + patterns[seq[index - 2]]
         else
           string << patterns[seq[index]] + patterns[seq[index - 1]]
         end
     else 
      string << patterns[seq[index]] * (num / seq[index])
     end }

    (0..6).to_a.reduce(param_hash) { |acc, index|
    if ((acc[:num] / acc[:seq][index]) > 0)
      then
      acc[:string] = substitute.call(acc[:num], acc[:seq], acc[:patterns], index, acc[:string])
      acc[:num]%=acc[:seq][index]
      acc
    else
        acc
    end}[:string]
  end
end
