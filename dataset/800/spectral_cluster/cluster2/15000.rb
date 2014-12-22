def are_anagrams(a,b); a.chars.sort==b.chars.sort; end
def get_anagrams(w); if w.length<=0;return;else; a=[]; o=[]; w.each do |e| if are_anagrams(w[0],e); a<<e; else o<<e; end; end; get_anagrams(o); @anagrams<<a; return @anagrams; end; end
def combine_anagrams(wo); @anagrams=[]; get_anagrams(wo); end
