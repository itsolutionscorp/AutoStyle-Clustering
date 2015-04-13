def compute(*strands)
    to_a = ->(string) { string.each_char.to_a }
    mutations = ->(args) { args.all? && args.first != args.last }
    b, a = strands.map(&to_a).sort
    a.zip(b).select(&mutations).count
  end