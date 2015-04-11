class DNA
  def initialize(arg)
    @arg = arg
  end

  def to_rna
    @arg.split("").collect!{ |x| x == 'T' ? x = 'U' : x }.join
  end
end

# I went for the one line approach simply because it 
# performed slightly faster when benchmarking. 
# Happy to learn other ways of refactoring!
