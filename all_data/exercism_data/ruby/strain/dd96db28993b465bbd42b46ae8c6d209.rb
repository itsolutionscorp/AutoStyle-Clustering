# added 'using Strain' to test file to use this refinement

module Strain
  refine Array do
    alias :keep :select
    alias :discard :reject
  end
end
