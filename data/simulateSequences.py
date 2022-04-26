import random

aas = "ABCDEFGHIKLMNPQRSTUVWXYZ"

def simulate(patterns, reference, numall, numtarget):
    res = []
    f = open(reference, "r")
    ref = "".join([x.strip() for x in f.read().split("\n")[1:]])
    f.close()
    f = open(patterns, "r")
    pat = [(int(x.split(",")[0][:-1]),x.split(",")[0][-1]) for x in f.read().split("\n")[1:] if len(x.strip()) != 0]
    f.close()
    for x in range(0, numtarget):
        selected = pat[random.randint(0, len(pat)-1)]
        mut = [x for x in ref]
        mut[selected[0]] = selected[1]
        res += ["".join(mut)]
    for x in range(0, numall-numtarget):
        nummut = random.randint(1,4)
        mut = [x for x in ref]
        for i in range(0, nummut):
            pos = random.randint(0, len(ref)-1)
            aa = aas[random.randint(0, len(aas)-1)]
            mut[pos] = aa
        res += ["".join(mut)]
    return res


seqs = simulate("GREB1_patterns.csv", "GREB1_reference.fasta", 400, 20)
f = open("site_sequences.fasta", "w")
for i in range(0, len(seqs)):
    f.write(">sequence_" + str(i) + "\n")
    f.write(seqs[i] + "\n")
f.close()
