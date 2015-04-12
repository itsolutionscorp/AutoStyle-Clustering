def compute(*strands)
    to_a = ->(string) { string.each_char.to_a }
    mutations = ->(args) { args.all? && args.uniq.one? }
    b, a = strands.map(&to_a).sort
    a.zip(b).select(&mutations).count
  end
end