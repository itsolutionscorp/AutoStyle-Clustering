def compute(strand_one, strand_two)
    strand_one.split('').zip(strand_two.split('')).select do | strand_pair |
      (!!strand_pair[0] && !!strand_pair[1]) &&
      (!strand_pair[0].eql? strand_pair[1])
    end.length
  end